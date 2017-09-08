/**
 * 
 */
package com.nobutnk.serialization.sample;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

/**
 * @author nobutnk
 *
 */
public class JavaStandardTest {

    private int loopCount = 100;
    
    @Test
    public void testJavaStandardSerialization()
            throws IOException, ClassNotFoundException {
        
        for (int i = 0; i < loopCount; i++) {
            execute();
        }
    }
    
    public void execute() throws IOException, ClassNotFoundException {
        StandardBook src = new StandardBook();
        src.setIsbn("978-4774169316");
        src.setTitle("Javaエンジニア養成読本 [現場で役立つ最新知識、満載!] ");
        src.setPrice(1980);
        src.setTags(new ArrayList<String>(
                Arrays.asList("あなたと", "Java", "今すぐ", "ダウンロード!")));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // シリアライズ
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(src);
        }

        byte[] binary = baos.toByteArray();

        // シリアライズ後のサイズ
        assertThat(binary).hasSize(350);

        // デシリアライズ
        try (ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(binary))) {
            StandardBook dest = (StandardBook) ois.readObject();

            assertThat(dest.getIsbn()).isEqualTo("978-4774169316");
            assertThat(dest.getTitle()).isEqualTo("Javaエンジニア養成読本 [現場で役立つ最新知識、満載!] ");
            assertThat(dest.getPrice()).isEqualTo(1980);
            assertThat(dest.getTags()).containsSequence("あなたと", "Java", "今すぐ", "ダウンロード!");
        }
    }

}
