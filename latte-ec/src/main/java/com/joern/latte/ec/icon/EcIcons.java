package com.joern.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by Joern on 2018/12/03.
 */

public enum EcIcons implements Icon{
    icon_scan('\ue64b'),
    icon_ali_pay('\ue604');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    public String key() {

        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
