package mttoolkit.event;

import mttoolkit.recognizer.Template;

import java.util.EventObject;

public class GestureEvent extends EventObject {

    private Template template;

    private double score;

    public GestureEvent(Template template, double score) {
        super(template);
        this.template = template;
        this.score = score;
    }

    public Template getTemplate() {
        return template;
    }

    public double getScore() {
        return score;
    }
}