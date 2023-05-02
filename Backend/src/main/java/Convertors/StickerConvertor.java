package Convertors;

import DTOs.StickerDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StickerConvertor {

    public static StickerDTO toStickerDTO(ResultSet stickersRS) throws SQLException {
        StickerDTO sticker = null;
        if (stickersRS != null) {
            while (stickersRS.next()) {
                sticker = new StickerDTO();
                sticker.setPlayer(stickersRS.getString("player"));
                sticker.setCountry(stickersRS.getString("country"));
                sticker.setNumber(stickersRS.getString("number"));
                sticker.setPhoto(stickersRS.getString("photo"));
            }
        }
        return sticker;
    }

    public static List<StickerDTO> toStickerDTOs(ResultSet stickersRS) throws SQLException {
        List<StickerDTO> stickers = new LinkedList<>();
        if (stickersRS != null) {
            while (stickersRS.next()) {
                stickers.add(toStickerDTO(stickersRS));
            }
        }
        return stickers;
    }

    public static List<String> toCountries(ResultSet countriesRS) throws SQLException {
        List<String> countries = new LinkedList<>();
        if (countriesRS != null) {
            while (countriesRS.next()) {
				countries.add(countriesRS.getString("country"));
            }
        }
        return countries;
    }

    public static List<String> toNumbers(ResultSet numbersRS) throws SQLException {
        List<String> numbers = new LinkedList<>();
        if(numbersRS != null) {
            while (numbersRS.next()) {
                numbers.add(numbersRS.getString("number"));
            }
        }
        return numbers;
    }
    
    public static StickerDTO toStickerDTO(Object stickerObject) {
        Map<String, String> stickerMap = (Map<String, String>)stickerObject;
        StickerDTO stickerDTO = new StickerDTO();
        stickerDTO.setCountry(stickerMap.get("country"));
        stickerDTO.setNumber(stickerMap.get("number"));
        stickerDTO.setPhoto(stickerMap.get("photo"));
        stickerDTO.setPlayer(stickerMap.get("player"));
        return stickerDTO;
    }
    
    public static List<StickerDTO> toStickerList(List<Object> stickerList) {
        List<StickerDTO> stickers = new LinkedList<>();
        for (Object stickObject: stickerList) {
            stickers.add(toStickerDTO(stickObject));
        }
        return stickers;
    }
}
