package com.qsmx.zww.servcie;

import com.qsmx.zww.mapper.SurnameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zww on 2019-03-07.
 */
@Service
public class SurnameServiceImpl implements SurnameService {

    @Autowired
    private SurnameMapper surnameMapper;

    @Override
    public List<Map<String, Object>> getSurname() {
        return surnameMapper.getsurname();
    }
}
