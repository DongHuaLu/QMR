package com.game.prompt.handler{

	import com.game.prompt.message.AddToFavoriteMessage;
	import net.Handler;

	public class AddToFavoriteHandler extends Handler {
	
		public override function action(): void{
			var msg: AddToFavoriteMessage = AddToFavoriteMessage(this.message);
			//TODO 添加消息处理
		}
	}
}