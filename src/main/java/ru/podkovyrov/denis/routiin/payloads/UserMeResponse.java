package ru.podkovyrov.denis.routiin.payloads;

import lombok.Data;
import ru.podkovyrov.denis.routiin.entities.User;

@Data
public class UserMeResponse {
    private long id;
    private String login;
    private String firstName;
    private String lastName;
    private long countOfRoutiins;
    private long followers;
    private long score;
    private String imageUrl;

    public UserMeResponse(User user){
        this.id = user.getId();
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.countOfRoutiins = user.getCards().size();
        this.followers = 0;
        this.score = user.getScore();
        this.imageUrl = user.getImageUrl();
    }
}
