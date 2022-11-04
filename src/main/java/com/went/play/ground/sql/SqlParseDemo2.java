package com.went.play.ground.sql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;

import java.util.List;
import java.util.Map;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/11/4 10:33
 */
public class SqlParseDemo2 {

    public static final String SQL = "SELECT t.a+t.b as 'a+b' FROM TAB t";

    public static void main(String[] args) throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(SQL);
        if (statement instanceof Select) {
            ((Select) statement).getSelectBody().accept(new SelectVisitorAdapter() {
                @Override
                public void visit(PlainSelect plainSelect) {
                    for (SelectItem selectItem : plainSelect.getSelectItems()) {
                        selectItem.accept(new SelectItemVisitorAdapter() {
                            @Override
                            public void visit(SelectExpressionItem item) {
                                item.getExpression().accept(new ExpressionVisitorAdapter(){
                                    @Override
                                    public void visit(Column column) {
                                        System.out.println((item.getAlias() == null ? column.getColumnName() : item.getAlias()) + " come from " + column.getTable() + "." + column.getColumnName());
                                    }
                                });
                            }
                        });
                    }
                }
            });
        }
    }
}
