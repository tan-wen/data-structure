package com.went.play.ground.sql;

import com.clickhouse.client.internal.google.common.collect.Lists;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.SelectItemVisitorAdapter;

import java.util.List;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/10/18 13:05
 */
public class SqlParseDemo {

    private static final String SQL = "INSERT INTO t1 (a, b, x) SELECT c,d,e+f FROM t2";

    public static void main(String[] args) throws JSQLParserException {

        Insert insert = (Insert) CCJSqlParserUtil.parse(SQL);
        String insertTableName = insert.getTable().getName();
        PlainSelect plainSelect = (PlainSelect) insert.getSelect().getSelectBody();

        for (int i = 0; i < plainSelect.getSelectItems().size(); i++) {
            SelectItem selectItem = plainSelect.getSelectItems().get(i);
            System.out.println("---------------------------------------------------------");
            List<String> inputColumns = Lists.newArrayList();
            selectItem.accept(new SelectItemVisitorAdapter() {
                @Override
                public void visit(SelectExpressionItem item) {

                    item.getExpression().accept(new ExpressionVisitorAdapter() {
                        @Override
                        public void visit(Column column) {
                            inputColumns.add(column.getFullyQualifiedName());
                        }
                    });
                }

            });
            System.out.printf("%s的第%d个字段%s，血缘字段：%s\n",
                    insertTableName, i + 1,
                    insert.getColumns().get(i).getColumnName(), inputColumns);
        }

    }

}
