package tdtu.demo.seminar;

import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View viewR = inflater.inflate(R.layout.fragment_register, container, false);
        final TextInputLayout regUsernameTextInput = viewR.findViewById(R.id.reg_username_TI);
        final TextInputEditText regUsernameEditText = viewR.findViewById(R.id.reg_username_ET);
        final TextInputLayout regPasswordTextInput = viewR.findViewById(R.id.reg_password_TI);
        final TextInputEditText regPasswordEditText = viewR.findViewById(R.id.reg_password_ET);
        final TextView backLogin = viewR.findViewById(R.id.lnkLogin);
        MaterialButton regButton = viewR.findViewById(R.id.btnRegistration);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isRegUsernameValid(regUsernameEditText.getText().toString())){
                    regUsernameTextInput.setError(getString(R.string.error_username));
                } else if (!isRegPasswordValid(regPasswordEditText.getText())) {
                    regPasswordTextInput.setError(getString(R.string.error_password));
                } else {
                    regUsernameTextInput.setError(null); // Clear error
                    regPasswordTextInput.setError(null); // Clear error
                    ((NavigationHost) getActivity()).navigateTo(new LoginFragment(), false); // Navigate to the next Fragment
                }
            }
        });

        regUsernameEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isRegUsernameValid(regUsernameEditText.getText().toString())) {
                    regUsernameTextInput.setError(null); //Clear the error
                }
                return false;
            }
        });
        regPasswordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isRegPasswordValid(regPasswordEditText.getText())) {
                    regPasswordTextInput.setError(null); //Clear the error
                }
                return false;
            }
        });

        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo(new LoginFragment(), false);
            }
        });

        return viewR;
    }
    private boolean isRegUsernameValid(String email) {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isRegPasswordValid(Editable password) {
        return password != null && password.length() >= 8;
    }
}
