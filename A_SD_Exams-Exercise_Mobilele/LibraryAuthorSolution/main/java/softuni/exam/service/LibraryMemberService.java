package softuni.exam.service;

import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.LibraryMember;

import java.io.IOException;

public interface LibraryMemberService {

    boolean areImported();

    String readLibraryMembersFileContent() throws IOException;
	
	String importLibraryMembers() throws IOException;

    LibraryMember findMemberById(Long cityId);

    void addAndSaveAddedStar(LibraryMember libraryMember, BorrowingRecord borrowingRecord);

    String exportLibraryMembers();
}
