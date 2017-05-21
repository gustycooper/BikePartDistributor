package basicStuff;

/** Define a person which can be extended
 *
 */
public class Person {
    private String firstName;
    private String lastName;
    private String email;

    public Person(String fn, String ln, String em) {
        firstName = fn;
        lastName = ln;
        email = em;
    }

    public String toString() { return firstName + " " + lastName + " " + email; }
}
