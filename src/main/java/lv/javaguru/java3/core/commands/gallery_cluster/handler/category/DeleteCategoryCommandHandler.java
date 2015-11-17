package lv.javaguru.java3.core.commands.gallery_cluster.handler.category;

import lv.javaguru.java3.core.commands.gallery_cluster.category.DeleteCategoryCommand;
import lv.javaguru.java3.core.commands.gallery_cluster.category.DeleteCategoryResult;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.gallery_cluster.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Aleksej_home on 2015.11.17..
 */
public class DeleteCategoryCommandHandler
        implements DomainCommandHandler<DeleteCategoryCommand, DeleteCategoryResult> {

    @Autowired CategoryService categoryService;

    @Override
    public DeleteCategoryResult execute(DeleteCategoryCommand command) throws Exception {
        long id = command.getCategoryId();
        if (id==0)
            throw new Exception();
        else
            categoryService.delete(id);
        return new DeleteCategoryResult();
    }

    @Override
    public Class getCommandType() {
        return DeleteCategoryCommand.class;
    }
}
