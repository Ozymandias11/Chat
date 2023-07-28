package pgdp;
import java.util.Arrays;
import java.util.Random;

public class Album{
    private String title;
    private int releaseYear;
    private Song[] songs;
    public Album(String title, int releaseYear){
        this.title = title;
        this.releaseYear = releaseYear;
    }
    public boolean contains(Song[] songs, Song song, int indexOfPointer){
       boolean contains = false;
        for (int i = 0; i <= indexOfPointer; i++) {
            if(song.isEqual(songs[i])){
                contains = true;
                break;
            }
        }
        return contains;
    }

   public int addSongs(Song[] songs) {
        if(songs == null)
            return 0;

        int numberOfSongsAdded = 0;
        if(this.songs == null){
            int count = 0; // to count duplicate elements
            int k = 0, m = 0, p = 0; // I'm using these variables to save duplicate element's indexes in an integer array.

            // number of duplicates
            for (int i = 0; i < songs.length; i++) {
                for (int j = i + 1; j <songs.length ; j++) {
                    if(songs[i].isEqual(songs[j])){
                        count++;
                        break;
                    }
                }
            }
            // count = 0 means I have no duplicates
            if(count == 0){
                this.songs = new Song[songs.length];
                for (int i = 0; i < songs.length; i++) {
                    this.songs[i] = songs[i];
                    numberOfSongsAdded++;
                }
            }else {
                int[] arrayOfDuplicates = new int[count]; // to count on which index we have duplicates;
                for (int i = 0; i < songs.length; i++) {
                    for (int j = i + 1; j < songs.length; j++) {
                        if ((songs[i].isEqual(songs[j]))) {
                           arrayOfDuplicates[k] = j;
                           k++;
                           break;
                        }
                    }
                }
                this.songs = new Song[songs.length - count];
                int count1 = 0;
                for (int i = 0; i < songs.length ; i++) {
                    for (int j = 0; j < arrayOfDuplicates.length; j++) {
                        if(i != arrayOfDuplicates[j]){
                           count1++;
                        }else
                            break;

                    }
                    if(count1 == arrayOfDuplicates.length){
                        this.songs[m] = songs[i];
                        m++;
                        numberOfSongsAdded++;
                    }
                    count1 = 0;
                }
            }
        }else{
            int K = 0;
            for (int i = 0; i < songs.length; i++) {
                if(!contains(this.songs, songs[i],this.songs.length - 1))       // to determine how many elements I have to add to new array.
                    K++;
            }
            Song[] tempArray = new Song[this.songs.length + K];
            int pointerIndex = this.songs.length - 1;
            System.arraycopy(this.songs,0,tempArray, 0, this.songs.length);
            for (int i = 0; i < songs.length; i++) {
                if(!contains(tempArray, songs[i],pointerIndex)){
                    tempArray[pointerIndex + 1] = songs[i];
                    pointerIndex++;
                    numberOfSongsAdded++;
                }
            }
            int numberOfNullsAdded = K - numberOfSongsAdded;
            Song[] newArray = new Song[tempArray.length - numberOfNullsAdded];
            for (int i = 0; i < newArray.length; i++) {
                newArray[i] = tempArray[i];
            }
            this.songs = newArray;
        }
        return numberOfSongsAdded;

   }
    public Song[] shuffle() {
        Random random = new Random();
        Song[] tempArray = new Song[this.songs.length];
        for (int i = 0; i < songs.length; i++) {
            tempArray[i] = this.songs[i];
        }
        for (int i = 0; i < this.songs.length; i++) {
            int randomIndex = random.nextInt(this.songs.length);
            Song temp = tempArray[randomIndex];
            tempArray[randomIndex] =tempArray[i];
            tempArray[i] = temp;

        }
        return tempArray;
    }
    public Song[] sortByTitle(boolean isAscending){
        Song[] tempSong = new Song[this.songs.length];
        for (int i = 0; i < tempSong.length; i++) {
            tempSong[i] = this.songs[i];
        }
        for (int i = 1; i < tempSong.length; i++) {
            for (int j = i; j > 0 ; j--) {
                if(isAscending){
                    if(tempSong[j].getTitle().compareTo(tempSong[j - 1].getTitle()) < 0){
                        Song temp = tempSong[j];
                        tempSong[j] = tempSong[j - 1];
                        tempSong[j] = temp;
                    }
                }else{
                    if(tempSong[j - 1].getTitle().compareTo(tempSong[j].getTitle()) > 0){
                        Song temp = tempSong[j];
                        tempSong[j] = tempSong[j - 1];
                        tempSong[j - 1] = temp;
                    }
                }
            }
        }
        return tempSong;
    }
    public Song[] sortByDuration(boolean isAscending){
        Song[] tempSong = new Song[this.songs.length];
        for (int i = 0; i < tempSong.length; i++) {
            tempSong[i] = this.songs[i];
        }
        for (int i = 1; i < tempSong.length; i++) {
            for (int j = i; j > 0; j--) {
                if(isAscending){
                    if(tempSong[j].getDuration() < (tempSong[j - 1].getDuration())){
                        Song temp = tempSong[j];
                        tempSong[j] = tempSong[j - 1];
                        tempSong[j - 1] = temp;
                    }
                }else{
                    if(tempSong[j - 1].getDuration() > tempSong[j].getDuration()){
                        Song temp = tempSong[j];
                        tempSong[j] = tempSong[j - 1];
                        tempSong[j - 1] = temp;
                    }
                }
            }
        }
        return tempSong;
    }
   public Song[] sortByReleaseYear(boolean isAscending){
        Song[] tempSong = new Song[this.songs.length];
        for (int i = 0; i < tempSong.length; i++) {
            tempSong[i] = this.songs[i];
        }
        for (int i = 1; i < tempSong.length; i++) {
            for (int j = i; j > 0; j--) {
                if(isAscending){
                    if(tempSong[j].getReleaseYear() < (tempSong[j - 1].getReleaseYear())){
                        Song temp = tempSong[j];
                        tempSong[j] = tempSong[j - 1];
                        tempSong[j - 1] = temp;
                    }
                }else{
                    if(tempSong[j].getReleaseYear() > tempSong[j - 1].getReleaseYear()){
                        Song temp = tempSong[j];
                        tempSong[j] = tempSong[j - 1];
                        tempSong[j - 1] = temp;
                    }
                }
            }
        }
        return tempSong;

    }
   public Song[] sortByPopularity(boolean isAscending){
        Song[] tempSong = new Song[this.songs.length];
        for (int i = 0; i < tempSong.length; i++) {
            tempSong[i] = this.songs[i];
        }
        for (int i = 1; i < tempSong.length; i++) {
            for (int j = i; j > 0 ; j--) {
                if(isAscending){
                    if(tempSong[j].getLikes() < (tempSong[j - 1].getLikes())){
                        Song temp = tempSong[j];
                        tempSong[j] = tempSong[j - 1];
                        tempSong[j - 1] = temp;
                    }
                }else{
                    if(tempSong[j].getLikes() > tempSong[j - 1].getLikes()){
                        Song temp = tempSong[j];
                        tempSong[j] = tempSong[j - 1];
                        tempSong[j - 1] = temp;
                    }
                }
            }
        }
        return tempSong;
    }
    public static  Song[] reverse(Song[] songs){
       Song[] song = new Song[songs.length];
        for (int i = 0; i < songs.length; i++) {
            song[i] = songs[songs.length - i - 1];
        }
        return song;
    }
    public  String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Title:").append(getTitle()).append(",Release year:").append(getReleaseYear()).append(",songs:").append("{");
        for (int i = 0; i < this.songs.length - 1; i++) {
            builder.append(songs[i].toString()).append("|");
        }
        builder.append(songs[this.songs.length - 1]);
        builder.append("}");
        return builder.toString();
    }
    public int totalLikesOfAlbum(){
        int totalLikes = 0;
        for (int i = 0; i < this.songs.length; i++) {
            totalLikes += this.songs[i].getLikes();
        }
        return totalLikes;
    }



    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public Song[] getSongs() {
        return this.songs;
    }
}