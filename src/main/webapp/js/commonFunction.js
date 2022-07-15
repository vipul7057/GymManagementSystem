function messageBox(msg)
{
	bootbox.dialog({
					closeButton:false,
					message:msg,
					buttons:{
						success:{
							label:"OK"
						}
					}
				})
}
