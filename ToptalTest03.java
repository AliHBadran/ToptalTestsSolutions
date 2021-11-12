package org.dataserver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ToptalTest03 {
    static String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    static class Photo {
        public String newName;
        public String city;
        public String extension;
        public Date date;
        public int originalSortIndex;

        public Photo(String city, String extension, String date, int originalSortIndex) throws ParseException {
            this.city = city;
            this.extension = extension;
            this.date = new SimpleDateFormat(DATE_FORMAT).parse(date);
            this.originalSortIndex = originalSortIndex;
        }
    }

    public static String solution(String s) throws ParseException {
        ArrayList<Photo> photos = new ArrayList<>();

        List<String> lines = Arrays.asList(s.split("\n"));
        for(int i = 0; i < lines.size(); i++){
            String line = lines.get(i);
            String[] photoInfo = line.split(", ");
            String city = photoInfo[1];
            String date = photoInfo[2];
            String extension = photoInfo[0].split("[.]")[1];

            photos.add(new Photo(city, extension, date, i));
        }

        Set<String> cities = photos.stream().map(photo -> photo.city).collect(Collectors.toSet());
        for(String city : cities) {
            List<Photo> sortedCityPhotos = photos.stream()
                    .filter(photo -> photo.city.equals(city))
                    .sorted(Comparator.comparingLong(photo -> photo.date.getTime()))
                    .collect(Collectors.toList());

            int cityPhotosCount = sortedCityPhotos.size();
            int zerosPaddingLength = (int) (Math.log10(cityPhotosCount) + 1);
            String leftPadStr = "%0" + zerosPaddingLength + "d";

            for(int i = 0; i < cityPhotosCount; i++) {
                Photo photo = sortedCityPhotos.get(i);
                photo.newName = String.format("%s%s.%s", city, String.format(leftPadStr, i + 1), photo.extension);
            }
        }

        return photos.stream().sorted(Comparator.comparingInt(photo -> photo.originalSortIndex)).map(photo -> photo.newName).collect(Collectors.joining("\n"));
    }

    public static void main(String[] args) throws ParseException {
        String s = "photo.jpg, Warsaw, 2013-09-05 14:08:15" + "\n";
        s += "john.png, London, 2015-06-20 15:13:22" + "\n";
        s += "myfriends.png, Warsaw, 2013-09-05 14:07:13" + "\n";
        s += "Eiffel.jpg, Paris, 2015-07-23 08:03:02" + "\n";
        s += "pisatower.jpg, Paris, 2015-07-22 23:59:59" + "\n";
        s += "BOB.jpg, London, 2015-08-05 00:02:03" + "\n";
        s += "notredame.png, Paris, 2015-09-01 12:00:00" + "\n";
        s += "me.jpg, Warsaw, 2013-09-06 15:40:22" + "\n";
        s += "a.png, Warsaw, 2016-02-13 13:33:50" + "\n";
        s += "b.jpg, Warsaw, 2016-01-02 15:12:22" + "\n";
        s += "c.jpg, Warsaw, 2016-01-02 14:34:30" + "\n";
        s += "d.jpg, Warsaw, 2016-01-02 15:15:01" + "\n";
        s += "e.png, Warsaw, 2016-01-02 09:49:09" + "\n";
        s += "f.png, Warsaw, 2016-01-02 10:55:32" + "\n";
        s += "g.jpg, Warsaw, 2016-02-29 22:13:11" + "\n";

        System.out.println(solution(s));
    }
}
