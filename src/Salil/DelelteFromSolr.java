package Salil;

import com.opencsv.CSVReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DelelteFromSolr {
    static int track=0;
    static  int syn=0;
    static int chunk = 1000;
    public static void main(String[] args)
    {
        int count=0;
        CSVReader reader = null;
        try
        {
            reader = new CSVReader(new FileReader("/home/salil/Downloads/new_ht_metadata.csv"),',','"',1);
            String [] nextLine;
            StringBuilder builder = new StringBuilder(100000);
            builder.append("<delete>");
            while ((nextLine = reader.readNext()) != null)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date date1 =sdf.parse("02-02-2016");
                //Date date1 = new Date();
                Date date2;
                try {
                    date2 = sdf.parse(nextLine[12]);
                }catch (Exception dpe){
                    continue;
                }

                if(date2.compareTo(date1)<0)
                {   track++;
                    count++;
                    builder.append("<id>"+nextLine[0]+"</id>");
                    if (count==chunk)                         //Here is use to take chunk of 1000 enteries and delete that & also makes
                    {
                        builder.append("</delete>");
                        String ar=builder.toString();
                        callhttp(ar);
                        System.out.println("----------------"+track);
                        count=0;
                        builder.delete(8,builder.length());
                    }
                }
            }
            if(count!=0)
            {
                track++;
                builder.append("</delete>");
                String result = builder.toString();
                System.out.println(result);
                callhttp(result);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+track);
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
    public static void callhttp(String str) throws UnsupportedEncodingException {
        syn++;

        System.out.println("function call : "+syn);

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://10.1.2.49:8080/solr/music-vas/update?commit=false");
        try {
            post.setHeader("Content-Type", "text/xml");
            HttpEntity entity = new ByteArrayEntity(str.getBytes("UTF-8"));
            post.setEntity(entity);
            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {System.out.println(line);}
        }
        catch (IOException e) {e.printStackTrace();}
    }

}