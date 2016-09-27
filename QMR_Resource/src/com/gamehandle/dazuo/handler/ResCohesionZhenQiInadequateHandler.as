package com.game.dazuo.handler{

	import com.game.dazuo.message.ResCohesionZhenQiInadequateMessage;
	import net.Handler;

	public class ResCohesionZhenQiInadequateHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCohesionZhenQiInadequateMessage = ResCohesionZhenQiInadequateMessage(this.message);
			//TODO 添加消息处理
		}
	}
}