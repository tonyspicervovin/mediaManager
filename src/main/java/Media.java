public  class Media {
    String name;
    int condition;
    String description;
    String media;
    double price;
    public MediaDB callit = new MediaDB();
    Media(String name, int condition,String description,String media,double price){
        this.name=name;
        this.condition=condition;
        this.description=description;
        this.media=media;
        this.price=price;



    }

    public String getName(){
        return name;
    }
    public int getCondition(){
        return condition;
    }
    public String getDescription(){
        return description;
    }
    public String getMedia(){
        return media;
    }
    public double getPrice(){
        return price;
    }

    public static class Movie extends Media {
        Movie(String name, int condition, String description, String media, double price) {
            super(name, condition, description, media, price);
        }
    }
}
