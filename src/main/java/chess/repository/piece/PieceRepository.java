package chess.repository.piece;

import chess.domain.piece.Piece;
import java.util.List;

public interface PieceRepository {

    long insert(long roomId, Piece piece);

    void update(Piece piece);

    void deleteAll();

    int count();

    Piece findPieceById(long pieceId);

    List<Piece> findPiecesByRoomId(long roomId);

    void deletePieceById(long id);
}
