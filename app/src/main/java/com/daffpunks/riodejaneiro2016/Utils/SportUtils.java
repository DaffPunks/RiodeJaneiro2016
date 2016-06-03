package com.daffpunks.riodejaneiro2016.Utils;

import com.daffpunks.riodejaneiro2016.R;

/**
 * Created by User on 30.04.2016.
 */
public class SportUtils {

    public static int getSport(int id){
        switch (id){
            case 2:
                return R.drawable.basquetebol;
            case 3:
                return R.drawable.boxe;
            case 18:
                return R.drawable.atletismo;
            default:
                return 0;
        }
    }
}
