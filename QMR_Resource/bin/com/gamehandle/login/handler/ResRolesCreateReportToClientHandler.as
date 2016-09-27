package com.game.login.handler{

	import com.game.login.message.ResRolesCreateReportToClientMessage;
	import net.Handler;

	public class ResRolesCreateReportToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRolesCreateReportToClientMessage = ResRolesCreateReportToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}