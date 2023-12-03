package program.managers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class AttributeManager {
    public void writeAttribute(Path path, String name, String value) throws IOException {
        UserDefinedFileAttributeView attributes = Files.getFileAttributeView(
                path,
                UserDefinedFileAttributeView.class
        );
        attributes.write(name, ByteBuffer.wrap(value.getBytes()));
    }

    public String readAttribute(Path path, String name) throws IOException {
        UserDefinedFileAttributeView attributes = Files.getFileAttributeView(
                path,
                UserDefinedFileAttributeView.class
        );
        ByteBuffer buffer = ByteBuffer.allocate(20);
        attributes.read(name, buffer);
        return new String(buffer.array());
    }

    public void deleteAttribute(String path, String name) throws IOException {
        UserDefinedFileAttributeView attributes = Files.getFileAttributeView(
                Paths.get(path),
                UserDefinedFileAttributeView.class
        );
        ByteBuffer buffer = ByteBuffer.allocate(20);
        attributes.delete(name);
    }
}
