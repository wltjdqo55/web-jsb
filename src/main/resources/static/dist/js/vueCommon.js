const vueFilters = {
    tel: function (v) {
        let vv = "";
        if (v == null) {
            vv = "";
        } else if (v.length == 8) {
            vv = v.replace(/(\d{4})(\d{4})/, '$1-$2');
        } else if (v.length == 9) {
            vv = v.replace(/(\d{2})(\d{3})(\d{4})/, '$1-$2-$3');
        } else if (v.length == 10) {
            if (v.indexOf('02') == 0) {
                vv = v.replace(/(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
            } else {
                vv = v.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
            }
        } else if (v.length == 11) {
            if (v.indexOf('050') == 0) {
                vv = v.replace(/(\d{4})(\d{3})(\d{4})/, '$1-$2-$3');
            } else {
                vv = v.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
            }
        } else if (v.length == 12) {
            vv = v.replace(/(\d{4})(\d{4})(\d{4})/, '$1-$2-$3');
        } else {
            vv = v;
        }
        return vv;
    },
    biz: function (v) {
        let vv = "";
        if (v == null) {
            vv = "";
        } else if (v.length == 10) {
            vv = v.replace(/(\d{3})(\d{2})(\d{5})/, '$1-$2-$3');
        } else {
            vv = v;
        }
        return vv;
    }
};