package algonquin.cst2335.ghos0042.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class MainViewModel extends ViewModel {
    public MutableLiveData<String> editString = new MutableLiveData<>();

    public MutableLiveData<Boolean> isSelect = new MutuableLiveData<>();
}
    //public String editString;

