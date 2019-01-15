package com.dms.expressmsg;

import com.dms.myexceptioin.DataBaseException;
import com.dms.myexceptioin.QueryResultIsNullException;
import com.dms.people.Student;

/*线程设置快递信息提醒*/
public class ExpressMassageRemind implements Runnable {

	private ExpressMassage expressMassage=null;
	public ExpressMassageRemind(Student stu){
		expressMassage = new ExpressMassage(stu);
	
	}
	@Override
	public void run() {
		while (true) {	
			try {
				Thread.sleep(expressMassage.getRemindTime()-100000);
				if(expressMassage!=null){
					expressMassage.showInfo();
					expressMassage.setVisible(true);
					Thread.sleep(100000);
				}

			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (DataBaseException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();

			} catch (QueryResultIsNullException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}

