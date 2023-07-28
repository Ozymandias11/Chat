package pgdp;

public class Artist {
      private String firstName;
    private String lastName;
    private int birthYear;
    private Album[] albums;
    private Song[] singles;

    public Artist(String firstName, String lastName, int birthYear, Album[] albums, Song[] singles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.albums = albums;
        this.singles = singles;

    }
    public Song mostLikedSong(){
      int maxLikes = Integer.MIN_VALUE;
      Song maxLikedSong = null;
        for (int i = 0; i < this.albums.length; i++) {
            for (int j = 0; j < this.albums[i].getSongs().length; j++) {
                if(maxLikes < this.albums[i].getSongs()[j].getLikes()){
                    maxLikes = this.albums[i].getSongs()[j].getLikes();
                    maxLikedSong = this.albums[i].getSongs()[j];
                }
            }
        }
        for (int i = 0; i < this.singles.length; i++) {
            if(maxLikes < this.singles[i].getLikes()){
                maxLikes = this.singles[i].getLikes();
                maxLikedSong = this.singles[i];
            }
        }
            return maxLikedSong;
    }
    public  Song leastLikedSong(){
        int leastLikes = Integer.MAX_VALUE;
        Song leastLikedSong = null;
        for (int i = 0; i < this.albums.length; i++) {
            for (int j = 0; j < this.albums[i].getSongs().length; j++) {
                if(leastLikes > this.albums[i].getSongs()[j].getLikes()){
                    leastLikes = this.albums[i].getSongs()[j].getLikes();
                    leastLikedSong = this.albums[i].getSongs()[j];
                }
            }
        }
        for (int i = 0; i <this.singles.length; i++) {
            if(leastLikes > this.singles[i].getLikes()){
                leastLikes = this.singles[i].getLikes();
                leastLikedSong = this.singles[i];
            }
        }
        return leastLikedSong;
    }
    public  int totalLikes(){
        int totalLikes = 0;
            for (int i = 0; i < this.albums.length; i++) {
               totalLikes += this.albums[i].totalLikesOfAlbum();
            }

           for (int i = 0; i < this.singles.length; i++) {
               totalLikes += this.singles[i].getLikes();
           }

        return totalLikes;
    }
    public boolean isEqual(Artist other){
        return this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName) && this.birthYear == other.birthYear;
    }
    public String toString(){
        return "Name:" + getFirstName() + " " + getLastName() + ",Birth year:" + getBirthYear() +  ",Total likes:" + totalLikes();

    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public Album[] getAlbums() {
        return albums;
    }

    public Song[] getSingles() {
        return singles;
    }
    
}
