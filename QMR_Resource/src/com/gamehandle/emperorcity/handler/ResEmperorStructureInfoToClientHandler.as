package com.game.emperorcity.handler{

	import com.game.emperorcity.message.ResEmperorStructureInfoToClientMessage;
	import net.Handler;

	public class ResEmperorStructureInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResEmperorStructureInfoToClientMessage = ResEmperorStructureInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}