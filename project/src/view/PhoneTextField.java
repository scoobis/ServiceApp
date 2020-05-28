package view;

import javafx.scene.control.TextField;

public class PhoneTextField extends TextField {

	public PhoneTextField() {

	}

	public PhoneTextField(String init) {
		super.setText(init);
	}

	@Override
	public void replaceText(int start, int end, String text) {
		if (validate(text)) {
			super.replaceText(start, end, text);
			verify();
		}
	}

	@Override
	public void replaceSelection(String text) {
		if (validate(text)) {
			super.replaceSelection(text);
			verify();
		}
	}

	private boolean validate(String text) {
		return text.matches("[0-9]*");
	}
	
	private void verify() {
        if (super.getText().length() > 10) {
            super.setText(super.getText().substring(0, 10));
        }
    }
}