package com.azmicro.scms.dto;

import com.azmicro.scms.model.Session;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SessionDto {

    private Long id;
    private String day;
    private String hourStart;
    private String hourEnd;
    private GradeDto gradeDto;

    public static SessionDto fromEntity(Session session){
        if (session == null) return null;
        return SessionDto.builder()
                .id(session.getId())
                .day(session.getDay())
                .hourStart(session.getHourStart())
                .hourEnd(session.getHourEnd())
                .gradeDto(GradeDto.fromEntity(session.getGrade()))
                .build();
    }

    public static Session toEntity(SessionDto sessionDto){
        Session session = new Session();
        session.setId(sessionDto.getId());
        session.setDay(sessionDto.getDay());
        session.setHourStart(sessionDto.getHourStart());
        session.setHourEnd(sessionDto.getHourEnd());
        session.setGrade(GradeDto.toEntity(sessionDto.getGradeDto()));
        return session;
    }
}
