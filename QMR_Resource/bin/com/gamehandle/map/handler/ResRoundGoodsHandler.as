package com.game.map.handler{

	import com.game.map.message.ResRoundGoodsMessage;
	import net.Handler;

	public class ResRoundGoodsHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRoundGoodsMessage = ResRoundGoodsMessage(this.message);
			//TODO 添加消息处理
		}
	}
}