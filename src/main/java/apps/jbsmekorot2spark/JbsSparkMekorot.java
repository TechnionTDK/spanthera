package apps.jbsmekorot2spark;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import spanthera.Span;
import spanthera.SpannedDocument;
import spanthera.io.Subject;
import spanthera.io.Tag;
import spanthera.io.TaggedSubject;
import spanthera.io.TaggerOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public abstract class JbsSparkMekorot {
    public static final int MINIMAL_PASUK_LENGTH = 2;
    public static final int MAXIMAL_PASUK_LENGTH = 14;
    private SparkSession sparkSession;

    public JbsSparkMekorot(SparkSession sparkSession)
    {
        this.sparkSession= sparkSession;
    }

    /**
     * arg[0] is the directory we want to work with. notice that it should be a directory without sub directories. e.g sub directory of jbs-text (like mesilatyesharim)
     * arg[1] is the output directory that the user want to get the results in.
     * @param args
     */
     public void main(String[] args)
    {
        if (args.length != 2) {
            System.out.println("Wrong arguments, should provide 2 arguments.");
            exit(0);
        }
        String dirPath= "hdfs://tdkstdsparkmaster:54310/"+ args[0];
        String dirName= new File(dirPath).getParentFile().getName();
        //String dirPath= "hdfs://tdkstdsparkmaster:54310/user/svitak/jbs-text/mesilatyesharim/mesilatyesharim.json.spark";
        String outDir = args[1];
        createFolderIfNotExists(outDir);
        TaggerOutput output = findPsukimInDirectoryAux(dirPath);
        try {
            PrintWriter writer = new PrintWriter(outDir + "/" + dirName+".json");
            writer.println("output file was created");
            writer.println(output.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void createFolderIfNotExists(String outputDirPath) {
        File dir = new File(outputDirPath);
        if (dir.isDirectory())
            return;
        dir.mkdir();
    }

    public TaggerOutput findPsukimInDirectoryAux(String dirPath) {
        TaggerOutput outputJson = new TaggerOutput();
        File dir = new File(dirPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".json.spark"));
        if(files== null || files.length==0) return outputJson;
        //String filepath = "hdfs://tdkstdsparkmaster:54310/user/orasraf/jbs-text/mesilatyesharim/mesilatyesharim.json.spark";
        String filepath =   dirPath+ "/*.json.spark";
        JavaRDD<Row> javaRDD = this.sparkSession.read().json(filepath).javaRDD();
        JavaRDD<List<Row>> matches = javaRDD.map(x->findPsukimInJson(x) );
        List<List<Row>> outPutJsonsList = matches.collect();
        for(List<Row> rowList : outPutJsonsList){
            Row row = rowList.get(0);
            outputJson.addTaggedSubject((TaggedSubject) row.get(0));
        }
        return outputJson;
    }

    public List<Row> findPsukimInJson(Row jSonName) {
        int TEXT_INDEX = 1;
        int URI_INDEX = 2;
        List<Row> retList = new ArrayList<>();
        Subject subject = new Subject((String) jSonName.get(URI_INDEX), (String) jSonName.get(TEXT_INDEX));

        // a subject denotes a specific text element within the json file

        TaggedSubject taggedSubject = new TaggedSubject();
        String text = subject.getText();
        String uri = subject.getUri();

        if (text == null || uri == null) {
            System.out.println("Subject " + uri + " has not text or uri " + "(" + jSonName + ")");
            return retList;
        }

        SpannedDocument sd = new SpannedDocument(text, MINIMAL_PASUK_LENGTH, MAXIMAL_PASUK_LENGTH);
        findPsukim(sd);
        // now we should output the result to a file & directory...
        taggedSubject.setUri(uri);
        for (Span span : sd.getAllSpans()) {
            if (span.getTags().size() == 0)
                continue;
            for (String tag : span.getTags()) {
                Tag t = new Tag(span.getStart(), span.getEnd(), tag);
                taggedSubject.addTag(t);
            }
        }

        Row row = RowFactory.create(taggedSubject);
        retList.add(row);
        return retList;
    }
    public abstract void findPsukim(SpannedDocument sd);
}