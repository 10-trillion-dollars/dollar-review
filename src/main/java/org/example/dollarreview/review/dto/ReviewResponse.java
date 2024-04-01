package org.example.dollarreview.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.dollarreview.review.entity.Review;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
    private Long id;
    private String content;
    private String photo;
    private int score;
    public ReviewResponse(Review review){
        this.id = review.getId();
        this.content = review.getContent();
        this.photo = review.getPhoto();
        this.score = review.getScore();
    }
}
