package chess.controller;

import chess.domain.dto.RoomDto;
import chess.domain.dto.RoomsDto;
import chess.domain.dto.move.MoveRequestDto;
import chess.domain.dto.move.MoveResponseDto;
import chess.serivce.chess.ChessService;
import java.sql.SQLException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room")
public class ChessRoomController {

    private final ChessService service;

    public ChessRoomController(final ChessService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@RequestBody RoomDto roomDto) {
        service.createRoom(roomDto.getName());
        return ResponseEntity.ok().body(roomDto);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<MoveResponseDto> enterRoom(@PathVariable("name") String roomName) {
        MoveResponseDto result = service.findPiecesByRoomName(roomName);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/{name}/end")
    public ResponseEntity<MoveResponseDto> endRoom(@PathVariable("name") String roomName) throws SQLException {
        MoveResponseDto result = service.end(roomName);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(value = "/{name}/move")
    public ResponseEntity<MoveResponseDto> move(@PathVariable("name") String roomName, @RequestBody MoveRequestDto moveRequestDto)
            throws SQLException {
        MoveResponseDto result = service.move(roomName, moveRequestDto.getSource(),
                moveRequestDto.getTarget());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<RoomsDto> showRooms() {
        RoomsDto result = service.showRooms();
        return ResponseEntity.ok().body(result);
    }
}
