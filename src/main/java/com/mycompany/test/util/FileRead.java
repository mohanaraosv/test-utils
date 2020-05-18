package com.mycompany.test.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class FileRead {

    public static void main(final String[] args) {
        String fileContent = getFileContent("domain/someService", "validFileNameAsKey");
        System.out.println("File Content :" + fileContent);
    }

    private static String getFileContent(final String directory, final String... keyParams) {
        String fileName = null;
        String module = null;
        try {
            fileName = StringUtils.join("mock/", directory, "/", StringUtils.join(Arrays.stream(keyParams)
                    .filter(s -> s.length() > 0).collect(Collectors.toList()), "_"), ".json");
            if (directory.contains("agreementRead") || directory.contains("profitCentreRead")) {
                module = "acc-admin-integration/";
            } else {
                module = "acc-admin-rsclients/";
            }
            Path filePath = Paths.get("C:/Projectdata/acc-admin/", module, "src/main/resources/", fileName);
            return Files.readString(filePath);
        } catch (IOException e) {
            System.out.println("Error in read file content {}:{}" + fileName + " " + e.getMessage());
            return null;
        }
    }
}
