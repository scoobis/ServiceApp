package view.essentials;

import javafx.scene.control.TextField;

public class DoubleTextField extends TextField {
	public DoubleTextField() {
	}

	public DoubleTextField(String init) {
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
		return text.matches("[0-9]*[.]?");
	}
	
	private void verify() {
        if (super.getText().length() > 8) {
            super.setText(super.getText().substring(0, 8));
        }
    }
}