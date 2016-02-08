package Salil;

import com.opencsv.CSVReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static Salil.XMLUTILLS.addFieldToDocElem;

public class UpdateSolrEntries {
    static int track=0;
    static  int syn=0;
    static int chunk = 1000;
    public static void main(String[] args)
    {

        HashSet <String> all = new HashSet<String>();
        int count=0;
        CSVReader reader = null;

        try
        {
            reader = new CSVReader(new FileReader("/home/salil/Downloads/new_ht_metadata.csv"),',','"',1);
            String [] nextLine;
            StringBuilder builder = new StringBuilder();
            builder.append("sys_unique_key:(");
            // builder.append("sys_unique_key:(009120700008375 OR 009121800002304 OR 009121300001496 OR 009121800006755 )");
            String result = builder.toString();

            HashMap<String,MusicVasMetaData> map = new HashMap<String, MusicVasMetaData>();
            while ((nextLine = reader.readNext()) != null)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                //Date date1 =sdf.parse("02-02-2016");
                Date date1 = new Date();
                Date date2;
                try {
                    date2 = sdf.parse(nextLine[12]);
                }catch (Exception dpe){
                    continue;
                }
                if(date2.compareTo(date1)>0)
                {

                    MusicVasMetaData musicVasMetaData = new MusicVasMetaData();
                    musicVasMetaData.AIRTELVCODE=nextLine[0];
                    musicVasMetaData.AIRTELCCODE=nextLine[1];
                    musicVasMetaData.SONGNAME=(nextLine[2]);
                    musicVasMetaData.SINGER=(nextLine[3]);
                    musicVasMetaData.MOVIENAME=(nextLine[4]);
                    musicVasMetaData.PAYTO=(nextLine[5]);
                    musicVasMetaData.CONTENTPROVIDERNAME=(nextLine[6]);
                    musicVasMetaData.GENRE=(nextLine[7]);
                    musicVasMetaData.SUB_GENRE=(nextLine[8]);
                    musicVasMetaData.LANGUAGENAME=(nextLine[9]);
                    musicVasMetaData.RIGHTSBODYNAME=(nextLine[10]);
                    musicVasMetaData.STARTDATE=(nextLine[11]);
                    musicVasMetaData.ENDDATE=(nextLine[12]);
                    musicVasMetaData.LABELNAME=(nextLine[13]);
                    musicVasMetaData.KEYWORDS=(nextLine[14]);
                    musicVasMetaData.SONGRELEASEDATE=(nextLine[15]);
                    musicVasMetaData.MOVIERELEASEDATE=(nextLine[16]);
                    musicVasMetaData.ACTOR=(nextLine[17]);
                    musicVasMetaData.MUSICDIRECTOR=(nextLine[18]);
                    musicVasMetaData.MOVIEDIRECTOR=(nextLine[19]);
                    musicVasMetaData.MOVIEPRODUCER=(nextLine[20]);
                    musicVasMetaData.SUBCATEGORYNAME=(nextLine[21]);
                    musicVasMetaData.SONGRELEASEYEAR=(nextLine[22]);
                    musicVasMetaData.MOVIERELEASEYEAR=(nextLine[23]);
                    musicVasMetaData.BINARYMAPID=(nextLine[24]);
                    musicVasMetaData.TRUNCATED_PAYTO=(nextLine[25]);
                    musicVasMetaData.TRUNCATED_CPNAME=(nextLine[26]);
                    musicVasMetaData.DESCRIPTION=(nextLine[27]);
                    musicVasMetaData.ISRC=(nextLine[28]);
                    map.put(musicVasMetaData.AIRTELVCODE,musicVasMetaData);
                    //System.out.println(musicVasMetaData);
                    track++;
                    count++;
                    builder.append(nextLine[0]+" OR ");
                    all.add(nextLine[0]);
                    if (count==chunk)                         //Here is use to take chunk of 1000 enteries and delete that & also makes
                    {
                        builder.delete(builder.length()-3,builder.length());
                        builder.append(")");
                        String ar=builder.toString();
                        long startTime = System.currentTimeMillis();

                        callhttp(ar,all,map);

                        long endTime   = System.currentTimeMillis();
                        long totalTime = endTime - startTime;
                        System.out.println("time : "+totalTime);
                        System.out.println("----------------"+track);
                        //System.out.println(map.size());
                        count=0;
                        map.clear();
                        builder.delete(16,builder.length());
                        all.clear();
                    }

                }
            }
            if(count!=0)
            {
                track++;
                builder.delete(builder.length()-3,builder.length());
                builder.append(")");
                String res = builder.toString();
                long startTime = System.currentTimeMillis();
                callhttp(res,all,map);

                long endTime   = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println("time : "+totalTime);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+track);
                //System.out.println(map.size());
                all.clear();
                map.clear();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {               //handling boundary  condition
            try {
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void callhttp(String str,HashSet<String> vcode,HashMap<String,MusicVasMetaData> map) throws UnsupportedEncodingException {
        syn++;

        System.out.println("function call : "+syn);

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://10.1.2.49:8080/solr/music-vas/select?wt=json");
        HashSet <String> find = new HashSet<String>();
        try {
            //post.setHeader("Content-Type", "text/xml");
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters.add(new BasicNameValuePair("q", str));
            parameters.add(new BasicNameValuePair("rows","1000"));
            // HttpEntity entity = new ByteArrayEntity(str.getBytes("UTF-8"));
            post.setEntity(new UrlEncodedFormEntity(parameters));
            System.out.println(post.getRequestLine());
            //post.setEntity(entity);
            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            StringBuilder builder = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
                builder.append(line).append("\n");
            }


            JSONParser parser = new JSONParser();
            Object obj = parser.parse(builder.toString());
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject jsonObject1= (JSONObject) jsonObject.get("response");
            long name = Long.valueOf(String.valueOf(jsonObject1.get("numFound")));

            //String name = String.valueOf(jsonObject1.get("numFound"));
            System.out.println("sizeof vcode : "+vcode.size());
            JSONArray ids =(JSONArray) jsonObject1.get("docs");
            for(Object x:ids)
            {
                JSONObject ft = (JSONObject) x;
                String keys = String.valueOf(ft.get("sys_unique_key"));
                find.add(keys);
            }
            vcode.removeAll(find);

            System.out.println("numFound are : "+name);
            System.out.println("sizeof find : "+find.size());
            System.out.println("sizeof vcode : "+vcode.size());

            createxml(vcode,map);

        }
        catch (IOException e) {e.printStackTrace();} catch (ParseException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {



        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void createxml(HashSet<String> vcode,HashMap<String,MusicVasMetaData> map) throws ParserConfigurationException, TransformerException, UnsupportedEncodingException {
        Iterator<String> itr = vcode.iterator();

        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = db.newDocument();
        Element addElem = document.createElement("add");
        while (itr.hasNext()){
            String temp=itr.next();
            MusicVasMetaData value =map.get(temp);
            String code=value.AIRTELVCODE.substring(2);
            String id ="srch_" + code;
            String mobileUrl = "";
            String thumbnailUrl="";
            Element docElem = document.createElement("doc");

            addFieldToDocElem(document, docElem, "id", id, false);
            addFieldToDocElem(document, docElem, "content_id", id, false);
            addFieldToDocElem(document, docElem, "sys_source", "56789_music_vas", false);
            addFieldToDocElem(document, docElem, "sys_unique_key", temp, false);
            addFieldToDocElem(document, docElem, "vcode", temp, false);
            addFieldToDocElem(document, docElem, "airtel_code", value.AIRTELCCODE, false);
            addFieldToDocElem(document, docElem, "category", "Music", false);
            addFieldToDocElem(document, docElem, "language", "en", false);
            addFieldToDocElem(document, docElem, "title_str", value.SONGNAME, false);
            addFieldToDocElem(document, docElem, "content_type", "music", false);
            addFieldToDocElem(document, docElem, "thumbnail_url", thumbnailUrl, false);
            addFieldToDocElem(document, docElem, "mobile_url", mobileUrl, false);
            addFieldToDocElem(document, docElem, "preview_url", "http://m.airtelhellotunes.in/wap_ai/playSong.php?vcode=" + code, false);
            addFieldToDocElem(document, docElem, "artist",value.SINGER, false);
            addFieldToDocElem(document, docElem, "album", value.MOVIENAME , false);
            addFieldToDocElem(document, docElem, "payto", value.PAYTO, false);
            addFieldToDocElem(document, docElem, "content_provider", value.CONTENTPROVIDERNAME, false);
            addFieldToDocElem(document, docElem, "genre", value.GENRE, false);
            addFieldToDocElem(document, docElem, "sub_genre", value.SUB_GENRE, false);
            addFieldToDocElem(document, docElem, "song_language", value.LANGUAGENAME, false);
            addFieldToDocElem(document, docElem, "label_name", value.LABELNAME, false);
            addFieldToDocElem(document, docElem, "keyword", value.KEYWORDS, false);
            addFieldToDocElem(document, docElem, "release_date_str", value.MOVIERELEASEDATE, false);
            addFieldToDocElem(document, docElem, "actor", value.ACTOR, false);
            addFieldToDocElem(document, docElem, "director", value.MUSICDIRECTOR, false);
            addFieldToDocElem(document, docElem, "movie_director", value.MOVIEDIRECTOR, false);
            addFieldToDocElem(document, docElem, "movie_producer", value.MOVIEPRODUCER, false);
            addFieldToDocElem(document, docElem, "category1", value.SUBCATEGORYNAME, false);
            addFieldToDocElem(document, docElem, "year_published", value.SONGRELEASEYEAR, false);
            addFieldToDocElem(document, docElem, "binarymapid", value.BINARYMAPID, false);
            addFieldToDocElem(document, docElem, "truncated_payto", value.TRUNCATED_PAYTO, false);
            addFieldToDocElem(document, docElem, "truncated_cpname", value.TRUNCATED_CPNAME, false);
            addFieldToDocElem(document, docElem, "desc", value.DESCRIPTION, false);
            // addFieldToDocElem(document, docElem, "comviva_song_id", comvivaSongId, false);
            // addFieldToDocElem(document, docElem, "ext_flv_id", comvivaSongId, false);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date date = new Date();
            String formattedCreationDate = sdf.format(date);
            addFieldToDocElem(document, docElem, "last_modify_date_dt", formattedCreationDate, false);


            StringWriter sw = new StringWriter();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(docElem), new StreamResult(sw));


            addElem.appendChild(docElem);
        }
        StringWriter sw = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(addElem), new StreamResult(sw));
        //System.out.println(sw.toString());
        postdata(sw.toString());

    }
    public static void postdata(String data) throws UnsupportedEncodingException {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://10.1.2.49:8080/solr/music-vas/update?commit=true");
        try {
            post.setHeader("Content-Type", "text/xml");
            ByteArrayInputStream bis = new ByteArrayInputStream(data.getBytes());
            HttpEntity entity = new InputStreamEntity(bis, bis.available());
            post.setEntity(entity);
            System.out.println(post.getRequestLine());
            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            StringBuilder builder = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
                builder.append(line).append("\n");
            }
        }
        catch (IOException e) {e.printStackTrace();}
    }

}
