#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.exceptions;

public class NotFoundException extends RuntimeException {
	public NotFoundException(String msg) {
		super(msg);
	}
}
