package org.csystem.util.collection.factory;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StringDataFactory {
    public static Collection<List<String>> getData()
    {
        try (var br = Files.newBufferedReader(Path.of("testdata/stringdata.txt"), StandardCharsets.UTF_8)) {
            var list = new ArrayList<List<String>>();

            String line;

            while ((line = br.readLine()) != null) {
                var lineList = new ArrayList<String>(Arrays.asList(line.split("[ ,]+")));
                list.add(lineList);
            }

            return list;
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
    public static Collection<List<String>> getDataWithEmptyList()
    {
        var coll = getData();

        coll.add(new ArrayList<>());

        return coll;
    }
}
