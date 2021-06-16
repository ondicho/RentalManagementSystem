package payment;

import androidx.annotation.NonNull;

public interface DarajaListener<Result> {
    void onResult(@NonNull Result result);

    void onError(String error);
}

