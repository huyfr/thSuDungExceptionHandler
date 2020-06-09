package cg.wbd.grandemonstration.formatter;

import cg.wbd.grandemonstration.model.ProvincesEntity;
import cg.wbd.grandemonstration.service.ProvinceService;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ProvinceFormatter implements Formatter<ProvincesEntity> {
    private ProvinceService provinceService;

    public ProvinceFormatter(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @Override
    public ProvincesEntity parse(String text, Locale locale) throws ParseException {
        return provinceService.findOne(Long.valueOf(text));
    }

    @Override
    public String print(ProvincesEntity object, Locale locale) {
        return object.toString();
    }
}
