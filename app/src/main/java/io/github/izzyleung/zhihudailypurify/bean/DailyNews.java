package io.github.izzyleung.zhihudailypurify.bean;

import com.annimon.stream.Stream;

import java.util.List;

import io.github.izzyleung.zhihudailypurify.support.lib.optional.Optional;

public final class DailyNews {
    private String date;
    private String dailyTitle;
    private String thumbnailUrl;
    private List<Question> questions;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDailyTitle() {
        return dailyTitle;
    }

    public void setDailyTitle(String dailyTitle) {
        this.dailyTitle = dailyTitle;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public boolean hasMultipleQuestions() {
        return this.getQuestions().size() > 1;
    }

    public Optional<DailyNews> updateQuestions(List<Question> questions) {
        if (Stream.of(questions).allMatch(Question::isValidZhihuQuestion)) {
            DailyNews result = copy(this);
            result.setQuestions(questions);
            return Optional.of(result);
        } else {
            return Optional.empty();
        }
    }

    public static Optional<DailyNews> createFromStory(Story story) {
        if (story.getDocument() == null) {
            return Optional.empty();
        }

        DailyNews result = new DailyNews();

        result.setDate(story.getDate());
        result.setThumbnailUrl(story.getThumbnailUrl());
        result.setDailyTitle(story.getDailyTitle());

        return Optional.of(result);
    }

    private static DailyNews copy(DailyNews news) {
        DailyNews result = new DailyNews();

        result.setDate(news.getDate());
        result.setDailyTitle(news.getDailyTitle());
        result.setThumbnailUrl(news.getThumbnailUrl());
        result.setQuestions(news.getQuestions());

        return result;
    }
}