package pgdp;

public class Song {
    private String title;
    private int releaseYear;
    private int duration = 60;
    private int likes = 0;

   public Song(String title, int releaseYear){
       this.title = title;
       this.releaseYear = releaseYear;
   }
   public Song(String title, int releaseYear, int duration){
       this.title = title;
       this.releaseYear = releaseYear;
       this.duration = duration;

   }
   public Song(String title, int releaseYear, int duration, int likes){
       this.title = title;
       this.releaseYear = releaseYear;
       this.duration = duration;
       this.likes = likes;
   }
    boolean changeDuration(int duration){
        return duration > 0 && duration < 720 && duration != getDuration();

    }

    public void setDuration(int duration) {
       if(changeDuration(duration))
           this.duration = duration;
    }
    public void like(){
       likes++;
    }
    public void unlike(){
      if(likes > 0)
          likes--;
    }
    boolean isEqual(Song other){
       return this.title.equals(other.title) && this.releaseYear == other.releaseYear && this.duration == other.duration;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getDuration() {
        return duration;
    }

    public int getLikes() {
        return likes;
    }
    public String toString(){
       return "Title:" + getTitle() + ",Duration:" + (float)getDuration() / 60 + " minutes,Release year:" + getReleaseYear() + ",Likes:" + getLikes();
    }


}
