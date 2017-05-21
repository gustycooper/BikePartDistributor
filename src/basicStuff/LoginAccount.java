package basicStuff;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by gusty on 5/4/17.
 * https://www.owasp.org/index.php/Hashing_Java
 */
public class LoginAccount {
    protected Person person;
    protected String userName;
    private String pwClear;
    protected byte[] passWord;
    protected byte[] nbyte;

    public LoginAccount() { }

    public LoginAccount(Person p, String userName, String passWord) {
        Random r = new Random();
        person = p;
        pwClear = passWord;
        this.userName = userName;
        nbyte = new byte[20];
        r.nextBytes(nbyte);
        this.passWord = hashPassword(passWord.toCharArray(), nbyte, 5, 256);
    }

    public boolean validateLoginAccount(String userName, String passWord) {
        return this.userName.equals(userName) &&
               Arrays.equals(this.passWord, hashPassword(passWord.toCharArray(), nbyte, 5, 256));
    }

    private byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return res;

        } catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     * The following two methods may be overridden, if desired when extending LoginAccount
     */
    public void login() {};
    public void logout() {};

    public String toString() {
        return person + " " + userName + " " + pwClear + " " + passWord;
    }
}
