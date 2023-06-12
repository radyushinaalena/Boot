package ru.skypro.lessons.springboot.weblibrary.dto;

import org.springframework.stereotype.Component;

import ru.skypro.lessons.springboot.weblibrary.pojo.Position;

@Component
public class PositionDTO {
    private int id;
    private String position;

    public static PositionDTO fromPosition(Position position) {
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setId(position.getId());
        positionDTO.setPosition(position.getPosition());
        return positionDTO;
    }

    public Position toPosition() {
        Position position = new Position();
        position.setId(this.getId());
        position.setPosition(this.getPosition());
        return position;
    }

    public PositionDTO(int id, String position) {
        this.id = id;
        this.position = position;
    }

    public PositionDTO(int id) {
        this.id = id;
    }

    public PositionDTO(String position) {
        this.position = position;
    }

    public PositionDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}