package sample.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Calculates checksum of the file (SHA1)
 *
 */
@Mojo( name = "checksumFile")
public class ChecksumFile extends AbstractMojo
{

    public enum HASH {
        SHA1,
        MD5,
        SHA256 // SHA-256
    }

    @Parameter
    private HASH algorytm;

    @Parameter
    private File plikWejsciowy;

    @Parameter
    private File plikZSumaKontrolna;

    public void execute() throws MojoExecutionException
    {
        getLog().info(plikWejsciowy.getAbsolutePath()
                + " " + plikZSumaKontrolna.getAbsolutePath()
                + " " + algorytm.name());

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(algorytm.name()=="SHA256" ? "SHA-256" : algorytm.name());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(plikWejsciowy.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] dataBytes = new byte[1024];

        int nread = 0;

        try {
            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        byte[] mdbytes = md.digest();
        try {
            FileOutputStream fos = new FileOutputStream(plikZSumaKontrolna.getAbsolutePath());
            fos.write(mdbytes);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}