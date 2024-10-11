package fr.pqndaa.perrokEssentials.utils;

public class CharRepo {

    public String getNeg(int value){
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < value; i++){
            sb.append("\uDAFF\uDFFF");
        }
        String strResult = sb.toString();
        return strResult;
    }
    public String getPos(int value){
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < value; i++){
            sb.append("\uDB00\uDC01");
        }
        String strResult = sb.toString();
        return strResult;
    }

}
