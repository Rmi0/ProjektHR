package sk.miscik.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * Created by client on 09.02.2018.
 */
public class User {

    private String firstName, lastName, email, token;
    private BufferedImage avatar;

    public User (String firstName, String lastName, String token, String email, String avatarURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.token = token;
        this.avatar = new BufferedImage(64,64,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) this.avatar.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        try {
            BufferedImage img = ImageIO.read(new URL(avatarURL));
            g2d.drawImage(img,0,0,this.avatar.getWidth(), this.avatar.getHeight(), null);
        } catch (Exception ex) {
            ex.printStackTrace();
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0,0,this.avatar.getWidth(),this.avatar.getHeight());
        }
        g2d.dispose();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BufferedImage getAvatar() {
        return avatar;
    }

    public void setAvatar(BufferedImage avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
