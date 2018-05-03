
/**
 * @author bruce
 * @date 2017-12-06
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
package cn.northpark.exception;


public class ResultUitl {
	
	public static Result success(Object object) {
		Result result=new Result();
		result.setCode(0);
		result.setMsg("成功");
		result.setData(object);
		return result;
	}
	
	public static Result success() {
		return success(null);
	}
	
	public static Result error(Integer code,String mes) {
		Result result=new Result();
		result.setCode(code);
		result.setMsg(mes);
		return result;
	}
	
//	抛出异常
//	 public void getAge(Integer id) throws Exception{
//        Girl girl = girlRepository.findOne(id);
//        Integer age = girl.getAge();
//        if (age < 10) {
//            //返回"你还在上小学吧" code=100
//            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
//        }else if (age > 10 && age < 16) {
//            //返回"你可能在上初中" code=101
//            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
//        }
//
//        //如果>16岁,加钱
//        //...
//    }
	
//	 /**
//     * 添加一个女生
//     * @return
//     */
//    @PostMapping(value = "/girls")
//    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
//        }
//
//        girl.setCupSize(girl.getCupSize());
//        girl.setAge(girl.getAge());
//
//        return ResultUtil.success(girlRepository.save(girl));
//    }

	
	
}
