package com.game.backpack.handler{

	import com.game.backpack.message.ResOpenCellInfoMessage;
	import net.Handler;

	public class ResOpenCellInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResOpenCellInfoMessage = ResOpenCellInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}