package com.game.player.handler{

	import com.game.player.message.ResRolesCreateReportMessage;
	import net.Handler;

	public class ResRolesCreateReportHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRolesCreateReportMessage = ResRolesCreateReportMessage(this.message);
			//TODO 添加消息处理
		}
	}
}