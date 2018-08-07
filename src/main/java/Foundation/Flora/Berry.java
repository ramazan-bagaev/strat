package Foundation.Flora;

import java.util.ArrayList;

public class Berry extends Plant {

    public Berry(int standartAmount) {
        super(standartAmount);
        name = "berry";
    }

    @Override
    public boolean isEdible(){
        return true;
    }
}
