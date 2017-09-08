/**
 * 
 */
package com.nobutnk.serialization.sample;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * @author nobutnk
 *
 */
public class KryoTest {
    
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
        
        Kryo kryo = new Kryo();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // シリアライズ
        try (Output output = new Output(baos)) {
            kryo.writeObject(output, src);
        }

        byte[] binary = baos.toByteArray();

        // シリアライズ後のサイズ
        assertThat(binary).hasSize(173);

        // デシリアライズ
        try (Input input = new Input(new ByteArrayInputStream(binary))) {
            StandardBook dest = kryo.readObject(input, StandardBook.class);

            assertThat(dest.getIsbn()).isEqualTo("978-4774169316");
            assertThat(dest.getTitle()).isEqualTo("Javaエンジニア養成読本 [現場で役立つ最新知識、満載!] ");
            assertThat(dest.getPrice()).isEqualTo(1980);
            assertThat(dest.getTags()).containsSequence("あなたと", "Java", "今すぐ", "ダウンロード!");
        }
    }

}
