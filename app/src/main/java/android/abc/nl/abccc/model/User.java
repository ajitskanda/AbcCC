package android.abc.nl.abccc.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.userdetails.UserDetails;

@Document(collection = "User")
public class User implements UserDetails {
    @Id
    private String id;
    private String firstname;
    private String insertion;
    private String lastname;
    private String email;
    private String password;
    private boolean hasPhoto;

    private boolean enabled;

    private boolean isPasswordCreated;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date dateAdded;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date dateEmployed;
    private String jobDescription;
    //Date dateOfBirth;

    private List<Role> roles;
    private List<String> contacts = new ArrayList<String>();
    private List<Address> addresses = new ArrayList<Address>();
    private List<Phone> phones = new ArrayList<Phone>();
    private List<Email> emails = new ArrayList<Email>();

    private String currentTeamName;

    public User() {
    }

    public User(String firstname, String insertion, String lastname, String email, String password, boolean hasPhoto, boolean enabled,
                boolean isPasswordCreated,
                List<Role> roles) {
        this.firstname = firstname;
        this.insertion = insertion;
        this.lastname = lastname;
        this.enabled = enabled;
        this.hasPhoto = hasPhoto;
        this.isPasswordCreated = isPasswordCreated;
        this.roles = roles;
        this.email = email;
        this.password = password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public void setAuthorities(List<Role> roles) {
        this.roles = roles;
    }

    public void setHasPhoto(boolean hasPhoto) {
        this.hasPhoto = hasPhoto;
    }

    public boolean getHasPhoto() {
        return hasPhoto;
    }

    @Override
    public Collection<Role> getAuthorities() {
        return roles;
    }

    public List<String> getContacts() {
        return contacts;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /*
     * Authorize user to login.
     */
    public void authorize() {
        this.enabled = true;
    }

    public String getInsertion() {
        return insertion;
    }

    public void setInsertion(String insertion) {
        this.insertion = insertion;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public String getCurrentTeamName() {
        return currentTeamName;
    }

    public void setCurrentTeamName(String currentTeamName) {
        this.currentTeamName = currentTeamName;
    }

    public boolean isPasswordCreated() {
        return isPasswordCreated;
    }

    public void setPasswordCreated(boolean isPasswordCreated) {
        this.isPasswordCreated = isPasswordCreated;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateEmployed() {
        return dateEmployed;
    }

    public void setDateEmployed(Date dateEmployed) {
        this.dateEmployed = dateEmployed;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}

