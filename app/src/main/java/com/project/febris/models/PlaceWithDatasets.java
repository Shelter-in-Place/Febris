package com.project.febris.models;


import androidx.room.Embedded;
import androidx.room.Relation;
import java.util.List;

public class PlaceWithDatasets {
    @Embedded
    public Place place;
    @Relation(
            parentColumn = "country_key_id",
            entityColumn = "country_key"
    )
    public List<Dataset> datasets;

    public Place getPlace() {
        return place;
    }

    public List<Dataset> getDatasets() {
        return datasets;
    }
}

//public class PlaceWithDatasets {
//    @Embedded
//    public Place place;
//    @Relation(
//            parentColumn = "country_key_id",
//            entityColumn = "country_key",
//            entity = OrderedDataset.class
//    )
//    public List<OrderedDataset> datasets;
//
//    public Place getPlace() {
//        return place;
//    }
//
//    public List<OrderedDataset> getDatasets() {
//        return datasets;
//    }
//}