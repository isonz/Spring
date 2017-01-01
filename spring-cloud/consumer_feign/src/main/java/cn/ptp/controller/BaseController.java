package cn.ptp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;

public class BaseController
{
    @Value("${cn.ptp.asset.public}")
    private String _public_;

    @Value("${cn.ptp.asset.private}")
    private String _asset_;

    @Value("${cn.ptp.value}")
    private String value;

    @Value("${cn.ptp.rang}")
    private int rang;

    public void asset(Model model)
    {
        model.addAttribute("__PUBLIC__", _public_);
        model.addAttribute("__ASSET__", _asset_);
    }

}
